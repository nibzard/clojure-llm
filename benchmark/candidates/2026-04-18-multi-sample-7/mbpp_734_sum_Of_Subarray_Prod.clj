(defn sum_Of_Subarray_Prod
  "	Write a cljthon function to find sum of products of all possible sublists of a given list. https://www.geeksforgeeks.org/sum-of-products-of-all-possible-subvectors/"
  [arr]
  (reduce (fn [acc x]
            (+ x (* acc (inc x))))
          0
          (or arr [])))