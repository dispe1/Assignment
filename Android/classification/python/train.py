import tensorflow as tf
import os
import time
from read import read_data
from inception_resnet_v2 import create_model

config = tf.ConfigProto()
config.gpu_options.allow_growth = True

batch_size = 32
learning_rate = 0.0001
epoch = 100
dir = 'data'

dataset, data_size = read_data(batch_size,dir)
epoch_size = data_size // batch_size
iterator = dataset.make_initializable_iterator()
image_stacked, label_stacked = iterator.get_next()
next_element = iterator.get_next()

x = tf.placeholder(tf.float32, shape = [None, 128, 128, 3], name='input')
y = tf.placeholder(tf.float32, shape = [None, 20])

logits = create_model(x)
y_pred = tf.nn.softmax(logits, name = 'output')
cost = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits_v2(logits=logits, labels=y))
optimizer = tf.train.AdamOptimizer(learning_rate=learning_rate).minimize(cost)
correct_prediction = tf.equal(tf.argmax(y_pred,1), tf.argmax(y,1))
accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32))

SAVER_DIR = "model"
saver = tf.train.Saver()
checkpoint_path = os.path.join(SAVER_DIR, "model")
ckpt = tf.train.get_checkpoint_state(SAVER_DIR)

with tf.Session(config=config) as sess:
    sess.run(tf.global_variables_initializer())
    sess.run(iterator.initializer)

    if ckpt and ckpt.model_checkpoint_path:
        saver.restore(sess, ckpt.model_checkpoint_path)
        print("restore model: ",ckpt.model_checkpoint_path)

    t1 = time.time()
    for step in range(epoch_size * epoch):
        images, labels = sess.run([image_stacked, label_stacked])
        sess.run(optimizer, feed_dict={x: images, y: labels})

        if (step % epoch_size) % batch_size == 0:
            saver.save(sess, checkpoint_path, global_step=step)
            tf.train.write_graph(sess.graph_def, 'model', 'trained.pb', as_text=False)
            tf.train.write_graph(sess.graph_def, 'model', 'trained.pbtxt', as_text=True)

            train_cost = cost.eval(feed_dict={x: images, y: labels})
            train_accuracy = accuracy.eval(feed_dict={x: images, y: labels})
            t2 = time.time()
            e = int(t2-t1)
            print('{:02d}:{:02d}:{:02d}'.format(e // 3600, (e % 3600 // 60), e % 60),"Epoch: %d, step: %d,  accuracy: %f, loss: %f" % (step / epoch_size, step % epoch_size, train_accuracy,train_cost))
