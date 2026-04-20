(defn next_smallest_palindrome
  "	Write a function to find the next smallest palindrome of a specified integer, returned as an integer."
  [num]
  (let [n (long (or num 0))
        digits (fn [x] (mapv #(- (int %) 48) (str x)))
        from-digits (fn [ds] (Long/parseLong (apply str ds)))
        palindrome? (fn [x]
                      (let [s (str x)]
                        (= s (apply str (reverse s)))))
        mirror-left (fn [ds]
                      (let [v (vec ds)
                            len (count v)]
                        (reduce (fn [acc i]
                                  (assoc acc (- len 1 i) (acc i)))
                                v
                                (range (quot len 2)))))
        increment-middle (fn [ds]
                           (let [len (count ds)]
                             (loop [v (vec ds)
                                    i (quot (dec len) 2)
                                    carry 1]
                               (if (neg? i)
                                 (vec (concat [1] (repeat (dec len) 0) [1]))
                                 (let [sum (+ (v i) carry)
                                       d (mod sum 10)
                                       c (quot sum 10)
                                       j (- len 1 i)
                                       v' (assoc v i d j d)]
                                   (if (zero? c)
                                     v'
                                     (recur v' (dec i) c)))))))]
    (cond
      (neg? n) nil
      (< n 9) (inc n)
      :else
      (let [n1 (inc n)]
        (if (palindrome? n1)
          n1
          (let [ds (digits n1)
                mirrored (mirror-left ds)
                p1 (from-digits mirrored)]
            (if (>= p1 n1)
              p1
              (from-digits (increment-middle mirrored))))))))