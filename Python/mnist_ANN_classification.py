from tensorflow.examples.tutorials.mnist import input_data
import tensorflow as tf
import time

mnist = input_data.read_data_sets("/tmp/data/", one_hot=True)

t1 = time.time()
num_steps = 5000
batch_size = 128
display_step = 500

n_hidden_1 = 256
n_hidden_2 = 256
n_hidden_3 = 256
num_input = 784
num_classes = 10

X = tf.placeholder(tf.float32, shape = [None, num_input])
Y = tf.placeholder(tf.float32, shape = [None, num_classes])

weights = {
    'h1' : tf.Variable(tf.random_normal([num_input, n_hidden_1])),
    'h2' : tf.Variable(tf.random_normal([n_hidden_1, n_hidden_2])),
    'h3' : tf.Variable(tf.random_normal([n_hidden_2, n_hidden_3])),
    'out' : tf.Variable(tf.random_normal([n_hidden_3, num_classes]))
}

biases = {
    'b1' : tf.Variable(tf.random_normal([n_hidden_1])),
    'b2' : tf.Variable(tf.random_normal([n_hidden_2])),
    'b3' : tf.Variable(tf.random_normal([n_hidden_3])),
    'out' : tf.Variable(tf.random_normal([num_classes]))
}

def mlp(x):
    L1 = tf.nn.relu(tf.add(tf.matmul(x, weights['h1']), biases['b1']))
    L2 = tf.nn.relu(tf.add(tf.matmul(L1, weights['h2']), biases['b2']))
    L3 = tf.nn.relu(tf.add(tf.matmul(L2, weights['h3']), biases['b3']))
    Lout = tf.matmul(L3, weights['out']) + biases['out']
    return Lout

logits = mlp(X)
cost = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits_v2(logits=logits, labels=Y))
optimizer = tf.train.AdamOptimizer(learning_rate=0.01).minimize(cost)

prediction = tf.nn.softmax(logits)
correct_pred = tf.equal(tf.argmax(prediction, 1), tf.argmax(Y,1))
accuracy = tf.reduce_mean(tf.cast(correct_pred, tf.float32))

with  tf.Session() as sess:
    sess.run(tf.global_variables_initializer())
    for step in range(1, num_steps+1):
        batch_train_images, batch_train_labels = mnist.train.next_batch(batch_size)
        sess.run(optimizer, feed_dict={X:batch_train_images, Y:batch_train_labels})
        if step % display_step == 0 or step == 1:
            print("Step " + str(step) + " out of " + str(num_steps))
    print("Optimization Finished!")
    t2 = time.time()
    print("Testing Accuracy: {:.1f}%".format(sess.run(accuracy,feed_dict={X: mnist.test.images, Y:mnist.test.labels})*100))
print("Learning Time: " + str(t2-t1) + " seconds")
