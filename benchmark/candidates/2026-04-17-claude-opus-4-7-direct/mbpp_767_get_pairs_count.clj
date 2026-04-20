(defn get_pairs_count
  "Count the number of pairs whose sum is equal to 'sum'."
  [arr sum]
  (if (empty? arr)
    0
    (loop [counts (frequencies arr)
           nums (keys counts)
           total 0]
      (if (empty? nums)
        total
        (let [n (first nums)
              c (counts n)
              complement (- sum n)
              c-comp (get counts complement)]
          (cond
            ;; If complement doesn't exist, skip
            (nil? c-comp) (recur counts (rest nums) total)
            ;; If n is half of sum, count is combination of c items taken 2 at a time
            (= n complement) (recur (dissoc counts n) 
                                   (rest nums) 
                                   (+ total (/ (* c (dec c)) 2)))
            ;; If n < complement, add product of frequencies
            (< n complement) (recur (dissoc counts n complement)
                                   (rest nums)
                                   (+ total (* c c-comp)))
            ;; If n > complement, pair already counted, skip
            :else (recur counts (rest nums) total)))))))