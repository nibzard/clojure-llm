(defn count_Substrings
  "Write a cljthon function to count the number of substrings with the sum of digits equal to their length."
  [s]
  (let [s (or s "")
        n (count s)]
    (loop [i 0
           total 0]
      (if (= i n)
        total
        (let [cnt (loop [j i
                         sum 0
                         len 0
                         c 0]
                    (if (= j n)
                      c
                      (let [digit (- (int (.charAt s j)) (int \0))
                            sum' (+ sum digit)
                            len' (inc len)]
                        (recur (inc j)
                               sum'
                               len'
                               (if (= sum' len') (inc c) c)))))]
          (recur (inc i) (+ total cnt))))))