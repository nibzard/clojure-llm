(defn vector_to_sha1
  "Given a vector of values, return the SHA-1 hash of their printed representation as a lowercase hex string.
  If the vector is empty, return nil.

  The function should hash the same string you would get from calling `pr-str` on the vector.

  Examples:
  >>> (vector_to_sha1 [1 2 3])
  \"9ef50cc82ae474279fb8e82896142702bccbb33a\"
  >>> (vector_to_sha1 [])
  nil"
  [items])