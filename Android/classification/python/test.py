import tensorflow as tf
import os
import shutil as st
import time
from read import read_test_data
from inception_resnet_v2 import create_model
config = tf.ConfigProto()
config.gpu_options.allow_growth = True

classes = []
dir = 'test'
f = open("labels.txt", 'r')
lines = f.readlines()
for line in lines:
    classes.append(line.strip('\n'))
f.close()

dataset, data_list = read_test_data(dir)
iterator = dataset.make_initializable_iterator()
image_stacked = iterator.get_next()

x =  tf.placeholder(tf.float32, shape = [None, 128, 128, 3])

logits = create_model(x)
y_pred = tf.nn.softmax(logits)

SAVER_DIR = "model"
saver = tf.train.Saver()
checkpoint_path = os.path.join(SAVER_DIR, "model")
ckpt = tf.train.get_checkpoint_state(SAVER_DIR)

with tf.Session(config=config) as sess:
    sess.run(tf.global_variables_initializer())
    sess.run(iterator.initializer)

    if ckpt and ckpt.model_checkpoint_path:
        saver.restore(sess, ckpt.model_checkpoint_path)
        for i in range(len(data_list)):
            images = sess.run(image_stacked)
            label = sess.run(tf.argmax(y_pred,1), feed_dict={x:images})
            print(classes[label[0]])
            print(data_list[i])
            st.move(data_list[i],'result/' + classes[label[0]])
