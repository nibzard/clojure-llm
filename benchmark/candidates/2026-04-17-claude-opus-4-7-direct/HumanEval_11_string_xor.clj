(defn string_xor
  "Input are two strings a and b consisting only of 1s and 0s.
  Perform binary XOR on these inputs and return result also as a string."
  [a b]
  (->> (map vector a b)
       (map (fn [[c1 c2]] (if (= c1 c2) \0 \1)))
       (apply str)))