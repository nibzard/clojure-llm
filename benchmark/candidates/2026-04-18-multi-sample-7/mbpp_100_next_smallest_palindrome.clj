(defn next_smallest_palindrome
  "	Write a function to find the next smallest palindrome of a specified integer, returned as an integer."
  [num]
  (letfn [(palindrome? [n]
            (let [s (str n)]
              (= s (apply str (reverse s)))))
          (make-palindrome [left odd?]
            (let [s (str left)
                  rs (apply str (reverse s))]
              (bigint
               (str s (if odd?
                        (subs rs 1)
                        rs)))))
          (next-pal [n]
            (let [s (str n)
                  len (count s)
                  odd? (odd? len)
                  half-len (quot (inc len) 2)
                  left-str (subs s 0 half-len)
                  candidate (make-palindrome left-str odd?)]
              (cond
                (> candidate n) candidate
                :else
                (let [inc-left (inc (bigint left-str))
                      inc-left-str (str inc-left)]
                  (if (> (count inc-left-str) half-len)
                    (bigint (str "1" (apply str (repeat (dec len) "0")) "1"))
                    (make-palindrome inc-left-str odd?)))))]
    (cond
      (nil? num) nil
      (neg? num) 0
      :else (next-pal num))))