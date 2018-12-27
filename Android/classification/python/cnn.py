import tensorflow as tf
keep_prob = 0.7

def leaky_relu(x):
    return tf.nn.leaky_relu(x, alpha=0.01)


def cnn(x):
    x_image = tf.reshape(x, [-1, 128, 128, 3])
    L1 = tf.layers.conv2d(inputs=x_image, filters=64, kernel_size=[3,3], padding='SAME', activation=tf.nn.relu)
    L1 = tf.layers.max_pooling2d(inputs=L1, pool_size=[2,2], padding='SAME', strides=2)
    L1 = tf.layers.batch_normalization(L1)

    L2 = tf.layers.conv2d(inputs=L1, filters=128, kernel_size=[3,3], padding='SAME', activation=tf.nn.relu)
    L2 = tf.layers.max_pooling2d(inputs=L2, pool_size=[2,2], padding='SAME', strides=2)
    L2 = tf.layers.batch_normalization(L2)

    L3 = tf.layers.conv2d(inputs=L2, filters=256, kernel_size=[3,3], padding='SAME', activation=tf.nn.relu)
    L3 = tf.layers.max_pooling2d(inputs=L3, pool_size=[2,2], padding='SAME', strides=2)
    L3 = tf.layers.batch_normalization(L3)

    L4 = tf.layers.conv2d(inputs=L3, filters=512, kernel_size=[3,3], padding='SAME', activation=tf.nn.relu)
    L4 = tf.layers.max_pooling2d(inputs=L4, pool_size=[2,2], padding='SAME', strides=2)
    L4 = tf.layers.batch_normalization(L4)

    L5 = tf.layers.conv2d(inputs=L4, filters=1024, kernel_size=[3,3], padding='SAME', activation=tf.nn.relu)
    L5 = tf.layers.max_pooling2d(inputs=L5, pool_size=[2,2], padding='SAME', strides=2)
    L5 = tf.layers.batch_normalization(L5)

    flat = tf.contrib.layers.flatten(L5)
    dr = tf.nn.dropout(flat, 0.8)
    out = tf.contrib.layers.fully_connected(inputs=dr, num_outputs=20, activation_fn=None)

    return out
