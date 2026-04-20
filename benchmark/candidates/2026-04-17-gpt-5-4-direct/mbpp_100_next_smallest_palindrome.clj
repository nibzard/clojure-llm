(defn next_smallest_palindrome
  "	Write a function to find the next smallest palindrome of a specified integer, returned as an integer."
  [num]
  (letfn [(palindrome? [n]
            (let [s (str n)]
              (= s (apply str (reverse s)))))]
    (loop [n (inc (long num))]
      (if (palindrome? n)
        n
        (recur (inc n))))))