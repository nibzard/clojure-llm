(defn problem-reverse-interleave [a b]
  (apply map list (partition b a)))