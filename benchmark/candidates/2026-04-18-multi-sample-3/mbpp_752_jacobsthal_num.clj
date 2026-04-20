(defn jacobsthal_num
  "Write a function to find the nth jacobsthal number. https://www.geeksforgeeks.org/jacobsthal-and-jacobsthal-lucas-numbers/ 0, 1, 1, 3, 5, 11, 21, 43, 85, 171, 341, 683, 1365, 2731, ..."
  [n]
  (cond
    (nil? n) nil
    (neg? n) nil
    :else
    (loop [i 0
           a 0
           b 1]
      (if (= i n)
        a
        (recur (inc i) b (+ b (* 2 a)))))))