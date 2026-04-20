(defn min-product-pair
  "Return the first pair [a b] from a collection of pairs whose product is the smallest.

  The input may be lazy or infinite, and pairs containing nil are ignored.
  If no valid pair exists, return nil.

  Examples:
  (min-product-pair [[3 4] [2 5] [1 9]])
  ;;=> [2 5]

  (min-product-pair [[nil 4] [7 1] [2 3]])
  ;;=> [7 1]"
  [pairs]
  (when-let [xs (seq (filter #(every? some? %) pairs))]
    (first (reduce (fn [[best-pair best-prod] pair]
                     (let [prod (apply * pair)]
                       (if (< prod best-prod)
                         [pair prod]
                         [best-pair best-prod])))
                   [(first xs) (apply * (first xs))]
                   (rest xs)))))