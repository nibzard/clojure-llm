(defn next_smallest_palindrome [num]
  (loop [n (inc num)]
    (let [s (str n)]
      (if (= (seq s) (reverse s))
        n
        (recur (inc n))))))