(defn cycpattern_check
  "You are given 2 words. You need to return true if the second word or any of its rotations is a substring in the first word
  >>> (cycpattern_check \"abcd\" \"abd\")
  false
  >>> (cycpattern_check \"hello\" \"ell\")
  true
  >>> (cycpattern_check \"whassup\" \"psus\")
  false
  >>> (cycpattern_check \"abab\" \"baa\")
  true
  >>> (cycpattern_check \"efef\" \"eeff\")
  false
  >>> (cycpattern_check \"himenss\" \"simen\")
  true"
  [a b]
  (let [a (or a "")
        b (or b "")
        n (count b)]
    (cond
      (zero? n) true
      (> n (count a)) false
      :else (let [doubled (str b b)]
              (boolean
               (some #(clojure.string/includes? a (subs doubled % (+ % n)))
                     (range n)))))))