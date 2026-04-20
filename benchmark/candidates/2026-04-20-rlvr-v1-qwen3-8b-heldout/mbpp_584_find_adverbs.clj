(defn find_adverbs
  "	Write a function to find the first adverb ending with ly and its positions in a given string."
  [text]
  (let [s (or text "")]
    (loop [idx 0
           n (count s)
           remaining (seq s)]
      (cond
        (> idx n) nil
        (and (<= idx n)
             (>= idx (- n 2))
             (and (contains? #{\a \e \i \o \u} (nth remaining 0))
                  (= (nth remaining 1) \l)
                  (= (nth remaining 2) \y)))
        [idx (+ idx 3)]
        :else (recur (inc idx) n remaining)))))