(defn summarize-nums
  "Write a function that takes a collection of numbers and returns a map with:
   :count  -> the number of numeric values
   :sum    -> the sum of the numeric values
   :avg    -> the average of the numeric values, or nil if there are no numbers

   The function must ignore nil values and any non-numeric items.
   It should work with any sequence type, including lazy sequences.

   Examples:
   >>> (summarize-nums [1 2 3])
   {:count 3, :sum 6, :avg 2.0}

   >>> (summarize-nums [1 nil :x 4 5])
   {:count 3, :sum 10, :avg 3.3333333333333335}

   >>> (summarize-nums [])
   {:count 0, :sum 0, :avg nil}"
  [items])