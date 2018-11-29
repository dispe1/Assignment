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
L1 = tf.layers.conv2d(inputs=X_img, filters=32, kernel_size=[3,3], padding='SAME', activation=tf.nn.relu)
L1 = tf.layers.max_pooling2d(inputs=L1, pool_size=[2,2], padding='SAME', strides=2)

#Convolution Layer L2
L2 = tf.layers.conv2d(inputs=L1, filters=64, kernel_size=[3,3], padding='SAME', activation=tf.nn.relu)
L2 = tf.layers.max_pooling2d(inputs=L2, pool_size=[2,2], padding='SAME', strides=2)

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
