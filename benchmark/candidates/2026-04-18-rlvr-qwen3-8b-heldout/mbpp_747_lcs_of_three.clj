(defn lcs_of_three
  "	Write a function to find the longest common subsequence for the given three string sequence. https://www.geeksforgeeks.org/lcs-longest-common-subsequence-three-strings/"
  [X Y Z]
  (let [a (or X "")
        b (or Y "")
        c (or Z "")
        n (count a)
        m (count b)
        p (count c)]
    (loop [i 0
           j 0
           k 0
           dp (vec (repeat (inc p) (vec (repeat (inc m) (vec (repeat (inc n) 0))))))]
      (if (or (>= i n) (>= j m) (>= k p))
        (let [result (reduce (fn [best v] (max best v)) Long/MIN_VALUE (map #(nth (nth (nth dp %1) %2) %3) (range (inc i)) (range (inc j)) (range (inc k))))]
          (if (neg? result)
            ""
            (loop [i i
                   j j
                   k k
                   acc []]
              (if (zero? i j k)
                (apply str (reverse acc))
                (let [v (nth (nth (nth dp i) j) k)]
                  (if (and (pos? i) (pos? j) (pos? k)
                           (= v (nth (nth (nth dp (dec i)) (dec j)) (dec k))))
                    (recur (dec i) (dec j) (dec k) (conj acc (nth a (dec i))))
                    (recur (if (zero? i) i (dec i))
                           (if (zero? j) j (dec j))
                           (if (zero? k) k (dec k))
                           acc)))))))
        (recur (inc i)
               (if (>= i n) j (inc j))
               (if (or (>= i n) (>= j m)) k (inc k))
               (assoc-in dp [i j k]
                         (if (and (pos? i) (pos? j) (pos? k)
                                  (= (nth a (dec i))
                                     (nth b (dec j))
                                     (nth c (dec k))))
                           (inc (nth (nth (nth dp (dec i)) (dec j)) (dec k)))
                          