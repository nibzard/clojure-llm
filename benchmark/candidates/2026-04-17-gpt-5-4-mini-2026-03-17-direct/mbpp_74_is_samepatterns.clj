(defn is_samepatterns
  "	Write a function to check whether it follows the sequence given in the patterns vector."
  [colors patterns]
  (let [c->p (zipmap colors patterns)
        p->c (zipmap patterns colors)]
    (and (= (count colors) (count patterns))
         (= (count c->p) (count p->c) (count colors))
         (every? true?
                 (map = patterns
                      (map c->p colors))))))