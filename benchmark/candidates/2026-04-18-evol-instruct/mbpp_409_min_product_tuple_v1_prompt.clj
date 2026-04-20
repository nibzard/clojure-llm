(defn min-product-pair
  "Return the first pair [a b] from a collection of pairs whose product is the smallest.

  The input may be lazy or infinite, and pairs containing nil are ignored.
  If no valid pair exists, return nil.

  Examples:
  (min-product-pair [[3 4] [2 5] [1 9]])
  ;;=> [2 5]

  (min-product-pair [[nil 4] [7 1] [2 3]])
  ;;=> [7 1]"
  [pairs])