(defn search
  [lst]
  (let [freqs (frequencies lst)]
    (reduce (fn [best [n f]]
              (if (and (pos? n) (>= f n) (> n best))
                n
                best))
            -1
            freqs)))