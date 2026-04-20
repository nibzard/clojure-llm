(defn next_smallest_palindrome
  "	Write a function to find the next smallest palindrome of a specified integer, returned as an integer."
  [num]
  (letfn [(palindrome? [n]
            (let [s (str n)]
              (= s (apply str (reverse s)))))]
    (loop [n (dec num)]
      (cond
        (nil? num) nil
        (neg? n) nil
        (palindrome? n) n
        :else (recur (dec n))))))