import tensorflow as tf

x_data = [[1,1],[2,2],[3,3]]
y_data = [[1],[2],[3]]
X = tf.placeholder(tf.float32, shape=[None,2])
Y = tf.placeholder(tf.float32, shape=[None,1])

W = tf.Variable(tf.random_normal([2,1]), name = 'weight')
b = tf.Variable(tf.random_normal([1]), name = 'bias')

hypothesis = tf.matmul(X,W) + b
cost = tf.reduce_mean(tf.square(hypothesis - Y))
train = tf.train.GradientDescentOptimizer(learning_rate=0.01).minimize(cost)

with tf.Session() as sess:
    sess.run(tf.global_variables_initializer())
    for step in range(2001):
            cost_val, W_val, b_val, _ = sess.run([cost, W, b, train], feed_dict={X:x_data, Y:y_data})
            if step % 200 == 0:
                print(step, cost_val, W_val, b_val)
