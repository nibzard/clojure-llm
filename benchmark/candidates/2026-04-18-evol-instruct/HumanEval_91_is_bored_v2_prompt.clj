(defn count-empty-collections
  "Return the number of empty collections in `colls`.

  Count only items that are collections and have zero elements.
  Ignore nil and non-collections.

  Examples:
  >>> (count-empty-collections [() [] {} #{1} nil \"hi\" (list 1 2)])
  3
  >>> (count-empty-collections [nil 42 \"abc\" '() [] {:a 1}])
  2"
  [colls])