(defn string_xor
  " Input are two strings a and b consisting only of 1s and 0s.
  Perform binary XOR on these inputs and return result also as a string.
  >>> (string_xor \"010\" \"110\")
  \"100\""
  [a b]
  (let [a (or a "")
        b (or b "")
        max-len (max (count a) (count b))
        a' (apply str (concat (repeat (- max-len (count a)) \0) a))
        b' (apply str (concat (repeat (- max-len (count b)) \0) b))]
    (apply str
           (map (fn [x y]
                  (cond
                    (and (= x \0) (= y \0)) \0
                    (and (= x \0) (= y \1)) \1
                    (and (= x \1) (= y \0)) \1
                    (and (= x \1) (= y \1)) \0))
                a' b'))))