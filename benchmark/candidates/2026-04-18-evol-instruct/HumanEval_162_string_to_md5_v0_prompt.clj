(defn vector_to_sha1
  "Given a vector of bytes, return its SHA-1 hash as a lowercase hex string.
  If the vector is empty or nil, return nil.

  Examples:
  >>> (vector_to_sha1 [72 101 108 108 111])
  \"f7ff9e8b7bb2b91af11a6f2c2c1d6f3b6d1f5f2f\"
  >>> (vector_to_sha1 [])
  nil"
  [bytes-v])