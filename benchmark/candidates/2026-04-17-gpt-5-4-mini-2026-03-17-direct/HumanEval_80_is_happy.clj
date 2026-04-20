(defn is_happy
  [s]
  (let [n (count s)]
    (and (>= n 3)
         (every? (fn [[a b c]]
                   (and (not= a b)
                        (not= a c)
                        (not= b c)))
                 (partition 3 1 s)))))