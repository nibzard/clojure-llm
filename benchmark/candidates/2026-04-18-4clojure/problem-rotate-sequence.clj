(defn problem-rotate-sequence [a b]
  (let [n (count b)
        r (mod a n)]
    (concat (drop r b) (take r b))))