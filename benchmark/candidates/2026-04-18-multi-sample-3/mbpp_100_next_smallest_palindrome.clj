(defn next_smallest_palindrome
  "	Write a function to find the next smallest palindrome of a specified integer, returned as an integer."
  [num]
  (let [n (long (or num 0))
        s (str n)
        len (count s)
        all-nine? (every? #(= % \9) s)
        make-pal (fn [left odd?]
                   (let [rs (apply str (reverse left))]
                     (str left (if odd? (subs rs 1) rs))))]
    (cond
      (< n 0) 0
      all-nine? (Long/parseLong (str "1" (apply str (repeat (dec len) "0")) "1"))
      :else
      (let [half-len (quot len 2)
            odd? (odd? len)
            left (subs s 0 (+ half-len (if odd? 1 0)))
            pal-str (make-pal left odd?)]
        (if (> (Long/parseLong pal-str) n)
          (Long/parseLong pal-str)
          (let [inc-left (str (inc (Long/parseLong left)))]
            (Long/parseLong (make-pal inc-left odd?))))))))