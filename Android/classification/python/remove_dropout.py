from __future__ import print_function
from tensorflow.core.framework import graph_pb2
import tensorflow as tf

def display_nodes(nodes):
    for i, node in enumerate(nodes):
        print('%d %s %s' % (i, node.name, node.op))
        [print(u'└─── %d ─ %s' % (i, n)) for i, n in enumerate(node.input)]


with tf.gfile.FastGFile("model/freeze.pb", 'rb') as f:
    graph = tf.GraphDef()
    graph.ParseFromString(f.read())


#display_nodes(graph.node)

graph.node[3920].input[0] = 'final/Flatten/flatten/Reshape'
# Remove dropout nodes
nodes = graph.node[:3904] + graph.node[3916:]

# Save graph
output_graph = graph_pb2.GraphDef()
output_graph.node.extend(nodes)
with tf.gfile.GFile('model/frozen_model_without_dropout.pb', 'w') as f:
    f.write(output_graph.SerializeToString())
