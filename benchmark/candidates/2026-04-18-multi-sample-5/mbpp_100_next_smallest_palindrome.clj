(defn next_smallest_palindrome
  "	Write a function to find the next smallest palindrome of a specified integer, returned as an integer."
  [num]
  (letfn [(palindrome? [n]
            (let [s (str n)]
              (= s (apply str (reverse s)))))
          (make-palindrome [left odd?]
            (let [s (str left)
                  rs (apply str (reverse s))]
              (Long/parseLong
               (if odd?
                 (str s (subs rs 1))
                 (str s rs)))))]
    (let [n (long (or num 0))]
      (cond
        (neg? n) 0
        :else
        (let [m (inc n)
              s (str m)
              len (count s)
              odd? (odd? len)
              half-len (quot (inc len) 2)
              left-str (subs s 0 half-len)
              left (Long/parseLong left-str)
              candidate (make-palindrome left odd?)]
          (if (>= candidate m)
            candidate
            (let [next-left (inc left)
                  next-left-str (str next-left)]
              (if (> (count next-left-str) half-len)
                (Long/parseLong
                 (str "1" (apply str (repeat (dec len) "0")) "1"))
                (make-palindrome next-left odd?))))))))