(defn next_smallest_palindrome
  "Write a function to find the next smallest palindrome of a specified integer, returned as an integer."
  [num]
  (let [n (Math/abs (long num))
        s (str n)
        len (count s)
        half (quot len 2)
        left (subs s 0 half)
        mid (when (odd? len) (subs s half (inc half)))
        make-pal (fn [left mid]
                   (let [rev-left (apply str (reverse left))]
                     (Long/parseLong (str left (or mid "") rev-left))))
        candidate (Long/parseLong
                   (if (odd? len)
                     (str left mid (apply str (reverse left)))
                     (str left (apply str (reverse left)))))
        mirrored (fn [s]
                   (let [chars (seq s)
                         len (count s)
                         half (quot len 2)
                         left (subs s 0 half)
                         mid (when (odd? len) (subs s half (inc half)))]
                     (Long/parseLong
                      (if (odd? len)
                        (str left mid (apply str (reverse left)))
                        (str left (apply str (reverse left)))))))]
    (let [pal (mirrored s)]
      (if (> pal n)
        pal
        (let [prefix (Long/parseLong (if (zero? half) "0" left))
              inc-prefix (str (inc prefix))
              new-len (count inc-prefix)]
          (if (> new-len half)
            (Long/parseLong (str 1 (apply str (repeat (dec len) 0)) 1))
            (let [new-left (subs (str (apply str (repeat half "0")) inc-prefix) (- new-len half))
                  new-left (if (< (count new-left) half)
                             (str (apply str (repeat (- half (count new-left)) "0")) new-left)
                             new-left)]
              (Long/parseLong
               (if (odd? len)
                 (str new-left (or mid "") (apply str (reverse new-left)))
                 (str new-left (apply str (reverse new-left)))))))))))