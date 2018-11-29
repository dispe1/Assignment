from tensorflow.examples.tutorials.mnist import input_data
import tensorflow as tf
import time

mnist = input_data.read_data_sets("MNIST_data/", one_hot=True)

training_epochs = 15
batch_size = 100

X = tf.placeholder(tf.float32, [None, 784])
Y = tf.placeholder(tf.float32, [None, 10])
X_img = tf.reshape(X, [-1,28,28,1])

#Convolution Layer L1
W1 = tf.Variable(tf.random_normal([3,3,1,32], stddev=0.01))
L1 = tf.nn.conv2d(X_img, W1, strides=[1,1,1,1], padding='SAME')
L1 = tf.nn.relu(L1)
L1 = tf.nn.max_pool(L1, ksize=[1,2,2,1], strides=[1,2,2,1], padding='SAME')

#Convolution Layer L2
W2 = tf.Variable(tf.random_normal([3,3,32,64], stddev=0.01))
L2 = tf.nn.conv2d(L1, W2, strides=[1,1,1,1], padding='SAME')
L2 = tf.nn.relu(L2)
L2 = tf.nn.max_pool(L2, ksize=[1,2,2,1], strides=[1,2,2,1], padding='SAME')

#Fully Connected Layer
L2_flat = tf.reshape(L2, [-1,7*7*64])
W3 = tf.Variable(tf.random_normal([7*7*64,10], stddev=0.01))
b3 = tf.Variable(tf.random_normal([10]))

logits = tf.matmul(L2_flat, W3) + b3
cost = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits_v2(logits=logits, labels=Y))
optimizer = tf.train.AdamOptimizer(learning_rate=0.001).minimize(cost)

#Session Run
with tf.Session() as sess:
    sess.run(tf.global_variables_initializer())
    print('Learning started.')
    t1 = time.time()
    for epoch in range(training_epochs):
        avg_cost = 0
        total_batch = int(mnist.train.num_examples / batch_size)
        for i in range(total_batch):
            batch_images, batch_labels = mnist.train.next_batch(batch_size)
            c, _ = sess.run([cost, optimizer], feed_dict={X: batch_images, Y: batch_labels})
            avg_cost += c/total_batch
        print('Epoch: ', '%04d' % (epoch+1), 'cost =', '{:.9f}'.format(avg_cost))
    print('Learning finished.')
    t2 = time.time()
    #Logging
    correct_prediction = tf.equal(tf.argmax(tf.nn.softmax(logits),1), tf.argmax(Y,1))
    accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32))
    print('Accuracy: ', sess.run(accuracy, feed_dict={X: mnist.test.images, Y:mnist.test.labels}))
print('Learning Time: ', str(t2-t1), ' seconds')
