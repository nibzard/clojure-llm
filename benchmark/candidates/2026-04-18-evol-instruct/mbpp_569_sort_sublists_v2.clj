(defn sort_sublists_by-length
  "Sort each sublist of strings by string length, preserving the outer list structure.

  Examples:
  (sort_sublists-by-length [[\"bb\" \"a\" \"ccc\"] [\"z\" \"yy\"]])
  => [[\"a\" \"bb\" \"ccc\"] [\"z\" \"yy\"]]

  Nil sublists are treated as empty lists:
  (sort_sublists_by-length [[\"bbb\" nil \"a\"] nil [\"cc\" \"d\"]])
  => [[nil \"a\" \"bbb\"] [] [\"d\" \"cc\"]]"
  [list1]
  (mapv (fn [sublist]
          (if (nil? sublist)
            []
            (sort-by count sublist)))
        list1))