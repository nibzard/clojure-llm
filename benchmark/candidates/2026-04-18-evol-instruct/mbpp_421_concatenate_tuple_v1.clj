(defn concatenate_nonblank
  "Return a single string by concatenating the non-blank elements of a collection with the given delimiter.

  Elements that are nil, empty, or whitespace-only are skipped.

  Examples:
  (concatenate_nonblank [\"a\" nil \"\" \"b\" \"  \" \"c\"] \",\") ;=> \"a,b,c\"
  (concatenate_nonblank [\"x\" \"y\" \"z\"] \"-\") ;=> \"x-y-z\"
  "
  [coll delimiter]
  (->> coll
       (keep #(when (and % (not (clojure.string/blank? (str %))))
                (str %)))
       (clojure.string/join delimiter)))