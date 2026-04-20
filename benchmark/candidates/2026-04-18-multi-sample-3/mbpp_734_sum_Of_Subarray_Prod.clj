(defn sum_Of_Subarray_Prod
  "	Write a cljthon function to find sum of products of all possible sublists of a given list. https://www.geeksforgeeks.org/sum-of-products-of-all-possible-subvectors/"
  [arr]
  (let [xs (seq arr)]
    (if (nil? xs)
      0
      (second
       (reduce (fn [[ending-total total] x]
                 (let [new-ending (+ x (* x ending-total))]
                   [new-ending (+ total new-ending)]))
               [0 0]
               xs)))))