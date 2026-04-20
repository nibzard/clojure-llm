(defn get_total_number_of_sequences
  "	Write a function that takes in positive integers m and n and finds the number of possible sequences of length n, such that each element is a positive integer and is greater than or equal to twice the previous element but less than or equal to m."
  [m n]
  (letfn [(count-seqs [max-val len]
            (cond
              (or (nil? max-val) (nil? len) (<= max-val 0) (<= len 0)) 0
              (= len 1) max-val
              :else
              (reduce + 0
                      (map #(count-seqs (quot max-val %2) (dec len))
                           (range 1 (inc max-val))))))]
    (letfn [(dp [max-val len memo]
              (if-let [v (get memo [max-val len])]
                [v memo]
                (cond
                  (or (<= max-val 0) (<= len 0)) [0 (assoc memo [max-val len] 0)]
                  (= len 1) [max-val (assoc memo [max-val len] max-val)]
                  :else
                  (loop [i 1
                         total 0
                         mem memo]
                    (if (> i max-val)
                      [total (assoc mem [max-val len] total)]
                      (let [[cnt mem'] (dp (quot max-val 2) (dec len) mem)]
                        (recur (inc i) (+ total (if (<= (* 2 i) max-val)
                                                  (first (dp (quot max-val 2) (dec len) mem'))
                                                  0))
                               mem')))))))]
      (letfn [(solve [max-val len memo]
                (if-let [v (get memo [max-val len])]
                  [v memo]
                  (cond
                    (or (<= max-val 0) (<= len 0)) [0 (assoc memo [max-val len] 0)]
                    (= len 1) [max-val (assoc memo [max-val len] max-val)]
                    :else
                    (loop [x 1
                           total 0
                           mem memo]
                      (if (> x max-val)
                        [total (assoc mem [max-val len] total)]
                        (if (> (* 2 x) max-val)
                          (recur (inc x) total mem)
                          (let [[sub mem'] (solve (quot max-val x) (dec len) mem)]
                            (recur (inc x) (+ total (first (solve (quot max-val 2) (dec len) mem'))) mem'))))))))]
        (letfn [(f [mm nn memo]
                  (if-let [v (get memo [mm nn])]
                    [v memo]
                    (cond
                      (or (<= mm 0) (<= nn 0)) [0 (assoc memo [mm nn] 0)]
                      (= nn 1) [mm (assoc memo [mm nn] mm)]
                      :else
                      (let [[res memo']
                            (loop [first-val 1
                                   total 0
                                   mem memo]
                              (if (> first-val mm)
                                [total mem]
                                (if (> (* 2 first-val) mm)
                                  (recur (inc first-val) total mem)
                                  (let [[sub mem'] (f (quot mm 2) (dec nn) mem)]
                                    (recur (inc first-val)
                                           (+ total (first (f (quot mm (* 2 first-val)) (dec nn) mem')))
                                           mem')))))]
                        [res (assoc memo' [mm nn] res)])))]
          (first
           ((fn g [mm nn memo]
              (if-let [v (get memo [mm nn])]
                [v memo]
                (cond
                  (or (<= mm 0) (<= nn 0)) [0 (assoc memo [mm nn] 0)]
                  (= nn 1) [mm (assoc memo [mm nn] mm)]
                  :else
                  (loop [k (quot mm 2)
                         total 0
                         mem memo]
                    (if (zero? k)
                      [total (assoc mem [mm nn] total)]
                      (let [[sub mem'] (g (quot mm 2) (dec nn) mem)]
                        (recur (dec k)
                               (+ total (first (g (quot mm (* 2 k)) (dec nn) mem')))
                               mem'))))))
            m n {}))))))