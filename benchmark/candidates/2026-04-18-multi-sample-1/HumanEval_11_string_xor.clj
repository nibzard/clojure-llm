(defn string_xor
  " Input are two strings a and b consisting only of 1s and 0s.
  Perform binary XOR on these inputs and return result also as a string.
  >>> (string_xor \"010\" \"110\")
  \"100\""
  [a b]
  (apply str (map (fn [ca cb] (if (= ca cb) \0 \1)) a b)))