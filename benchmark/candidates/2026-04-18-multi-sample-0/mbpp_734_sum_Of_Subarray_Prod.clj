(defn sum_Of_Subarray_Prod
  "	Write a cljthon function to find sum of products of all possible sublists of a given list. https://www.geeksforgeeks.org/sum-of-products-of-all-possible-subvectors/"
  [arr]
  (let [xs (seq arr)]
    (if (nil? xs)
      0
      (reduce (fn [acc x]
                (+ x (* x acc)))
              0
              xs))))