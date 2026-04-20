(defn contains-subvector?
  "Return true if `needle` appears as a contiguous subvector inside `haystack`.

  Examples:
  (contains-subvector? [2 3] [1 2 3 4]) => true
  (contains-subvector? [] [1 2 3])      => true
  (contains-subvector? [4 5] [1 2 3])    => false"
  [needle haystack])