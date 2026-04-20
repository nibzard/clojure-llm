(defn count-nested-vectors
  "Return the number of vector values contained anywhere in the given nested collection.
   Nil values are ignored. Works with lists, vectors, sets, maps, and sequences.

   Examples:
   (count-nested-vectors [1 [2 3] {:a [4]} nil]) ;=> 2
   (count-nested-vectors '(1 2 [3 [4]] #{[5]}))   ;=> 3"
  [input])