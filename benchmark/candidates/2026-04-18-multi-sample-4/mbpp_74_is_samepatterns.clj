(defn is_samepatterns
  "	Write a function to check whether it follows the sequence given in the patterns vector."
  [colors patterns]
  (let [colors   (or colors [])
        patterns (or patterns [])]
    (cond
      (empty? patterns) (empty? colors)
      (empty? colors) false
      :else
      (loop [cs colors
             ps patterns]
        (cond
          (empty? cs) true
          (empty? ps) false
          (= (first cs) (first ps)) (recur (rest cs) (rest ps))
          :else
          (let [p (vec patterns)
                plen (count p)
                remaining (count cs)]
            (if (> plen remaining)
              false
              (loop [i 0]
                (cond
                  (= i plen) (recur cs p)
                  (not= (nth cs i) (nth p i)) false
                  :else (recur (inc i))))))))))