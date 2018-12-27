import tensorflow as tf
import sys
from read import read_test_data

config = tf.ConfigProto()
config.gpu_options.allow_growth = True

dir = 'test'
dataset, data_list = read_test_data(dir)
iterator = dataset.make_initializable_iterator()
image_stacked = iterator.get_next()


label_lines = [line.rstrip() for line
                   in tf.gfile.GFile("label.txt")]

with tf.gfile.FastGFile("model/frozen_model_without_dropout.pb", 'rb') as f:
    graph_def = tf.GraphDef()
    graph_def.ParseFromString(f.read())
    _ = tf.import_graph_def(graph_def, name='')

with tf.Session(config=config) as sess:
    sess.run(iterator.initializer)
    for i in range(len(data_list)):
        images = sess.run(image_stacked)

        softmax_tensor = sess.graph.get_tensor_by_name('output:0')

        predictions = sess.run(softmax_tensor, {'input:0': images})

        top_k = predictions[0].argsort()[-len(predictions[0]):][::-1]
        print(data_list[i])

        for node_id in top_k[0:5]:
            human_string = label_lines[node_id]
            score = predictions[0][node_id]
            print('%s (score = %.5f)' % (human_string, score))
