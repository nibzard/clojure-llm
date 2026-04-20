(defn sum-deep
  "Return the sum of all numeric values in a nested collection.

  The input may contain vectors, lists, sets, maps, strings, nils, and numbers.
  Only numeric values should be counted; everything else is ignored.

  Examples:
  (sum-deep [1 [2 nil] {:a 3} \"x\" #{4 5}]) ;=> 15
  (sum-deep nil) ;=> 0
  (sum-deep [1 :a \"2\" [3.5]]) ;=> 4.5"
  [data])