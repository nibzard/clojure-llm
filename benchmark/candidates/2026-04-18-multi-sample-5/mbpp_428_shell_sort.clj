(defn shell_sort
  "	Write a function to sort the given vector by using shell sort."
  [my_list]
  (let [v (vec (or my_list []))
        n (count v)]
    (loop [gap (quot n 2)
           arr v]
      (if (zero? gap)
        arr
        (let [arr'
              (loop [i gap
                     a arr]
                (if (>= i n)
                  a
                  (let [temp (nth a i)
                        a'
                        (loop [j i
                               a2 a]
                          (if (and (>= j gap)
                                   (> (nth a2 (- j gap)) temp))
                            (recur (- j gap) (assoc a2 j (nth a2 (- j gap))))
                            (assoc a2 j temp)))]
                    (recur (inc i) a'))))]
          (recur (quot gap 2) arr')))))