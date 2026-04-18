(defn find_adverbs
  "	Write a function to find the first adverb ending with ly and its positions in a given string."
  [text]
  (let [s (or text "")
        n (count s)]
    (loop [i 0]
      (cond
        (= i n) nil
        (and (<= i n)
             (<= (inc i) n)
             (<= (+ i 2) n)
             (= \- (nth s i))
             (= \l (nth s (inc i)))
             (= \y (nth s (+ i 2))))
        [i (inc i) (+ i 2)]
        :else (recur (inc i))))))