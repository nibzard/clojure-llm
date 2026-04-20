(defn problem-nth-element [a b]
  (if (zero? b)
    (first a)
    (recur (rest a) (dec b))))