(defn next_smallest_palindrome
  "	Write a function to find the next smallest palindrome of a specified integer, returned as an integer."
  [num]
  (letfn [(palindrome? [n]
            (let [s (str n)]
              (= s (apply str (reverse s)))))
          (make-palindrome [left odd?]
            (let [s (str left)
                  rev-part (apply str (reverse (if odd?
                                               (subs s 0 (dec (count s)))
                                               s)))]
              (Long/parseLong (str s rev-part))))]
    (cond
      (nil? num) nil
      (neg? num) nil
      :else
      (let [s (str num)
            len (count s)
            odd? (odd? len)
            half-len (quot (+ len 1) 2)
            left (Long/parseLong (subs s 0 half-len))
            candidate (make-palindrome left odd?)]
        (if (> candidate num)
          candidate
          (let [next-left (inc left)
                next-left-str (str next-left)]
            (if (> (count next-left-str) half-len)
              (Long/parseLong
               (str next-left-str
                    (apply str (reverse (subs next-left-str 0 (if odd? 1 0))))))
              (make-palindrome next-left odd?)))))))