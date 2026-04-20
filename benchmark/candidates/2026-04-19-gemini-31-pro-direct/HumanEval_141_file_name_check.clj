(defn file_name_check [file_name]
  (let [digits-count (count (filter #(<= (int \0) (int %) (int \9)) file_name))
        dots-count (count (filter #(= \. %) file_name))]
    (if (and (<= digits-count 3)
             (= dots-count 1))
      (let [dot-idx (.indexOf ^String file_name ".")
            before-dot (subs file_name 0 dot-idx)
            after-dot (subs file_name (inc dot-idx))
            f (first before-dot)]
        (if (and f
                 (or (<= (int \a) (int f) (int \z))
                     (<= (int \A) (int f) (int \Z)))
                 (#{"txt" "exe" "dll"} after-dot))
          "Yes"
          "No"))
      "No")))