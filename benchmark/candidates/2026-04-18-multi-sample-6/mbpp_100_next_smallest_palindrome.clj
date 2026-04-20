(defn next_smallest_palindrome
  "	Write a function to find the next smallest palindrome of a specified integer, returned as an integer."
  [num]
  (letfn [(pow10 [n]
            (loop [i n
                   acc 1N]
              (if (zero? i)
                acc
                (recur (dec i) (* acc 10N)))))
          (digits->num [ds]
            (reduce (fn [acc d] (+ (* acc 10N) d)) 0N ds))
          (make-pal [left odd?]
            (let [left-ds (mapv #(- (int %) 48) (seq (str left)))
                  right-ds (if odd?
                             (rseq (pop left-ds))
                             (rseq left-ds))]
              (digits->num (concat left-ds right-ds))))]
    (let [n (bigint (or num 0))
          s (str n)
          len (count s)
          odd? (odd? len)
          left-len (quot (+ len 1) 2)
          left (bigint (subs s 0 left-len))
          pal (make-pal left odd?)]
      (if (> pal n)
        pal
        (let [inc-left (inc left)
              inc-left-len (count (str inc-left))]
          (if (> inc-left-len left-len)
            (+ 1N (pow10 len) 1N)
            (make-pal inc-left odd?)))))))