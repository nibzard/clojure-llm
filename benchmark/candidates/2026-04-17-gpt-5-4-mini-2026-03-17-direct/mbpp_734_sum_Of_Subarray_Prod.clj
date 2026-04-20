(defn sum_Of_Subarray_Prod
  "	Write a cljthon function to find sum of products of all possible sublists of a given list. https://www.geeksforgeeks.org/sum-of-products-of-all-possible-subvectors/"
  [arr]
  (let [arr (seq arr)]
    (if (nil? arr)
      0
      (second
       (reduce (fn [[sum-so-far ending-here] x]
                 (let [new-ending-here (+ x (* ending-here x))
                       new-sum (+ sum-so-far new-ending-here)]
                   [new-sum new-ending-here]))
               [0 0]
               arr)))))