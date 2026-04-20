(defn shell_sort
  "Write a function to sort the given vector by using shell sort."
  [my_list]
  (let [v (vec my_list)
        n (count v)]
    (loop [gap (quot n 2)
           arr v]
      (if (zero? gap)
        arr
        (let [new-arr
              (loop [i gap
                     a arr]
                (if (< i n)
                  (let [a' (loop [j i
                                  a-inner a]
                             (if (and (>= j gap)
                                      (< (a-inner j) (a-inner (- j gap))))
                               (recur (- j gap)
                                      (assoc a-inner
                                             j (a-inner (- j gap))
                                             (- j gap) (a-inner j)))
                               a-inner))]
                    (recur (inc i) a'))
                  a))]
          (recur (quot gap 2) new-arr))))))