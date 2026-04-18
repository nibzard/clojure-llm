(defn string_xor
  " Input are two strings a and b consisting only of 1s and 0s.
  Perform binary XOR on these inputs and return result also as a string.
  >>> (string_xor \"010\" \"110\")
  \"100\""
  [a b]
  (let [a (or a "")
        b (or b "")
        max-len (max (count a) (count b))
        a-chars (apply str (concat (repeat (- max-len (count a)) \0) a))
        b-chars (apply str (concat (repeat (- max-len (count b)) \0) b))
        xor-chars (map #(if (= % \0) \1 \0) (map ^char xor a-chars b-chars))]
    (apply str xor-chars)))