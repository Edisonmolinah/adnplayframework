/*
 * Copyright (C) Lightbend Inc. <https://www.lightbend.com>
 */

package persistencia;

import play.db.Database;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Singleton
class JavaApplicationDatabase {

    private Database db;
    private DatabaseExecutionContext executionContext;

    @Inject
    public JavaApplicationDatabase(Database db, DatabaseExecutionContext context) {
        this.db = db;
        this.executionContext = executionContext;
    }

    public CompletionStage<Integer> updateSomething() {
        return CompletableFuture.supplyAsync(
                () -> {
                    return db.withConnection(
                            connection -> {
                                // do whatever you need with the db connection
                                return 1;
                            });
                },
                executionContext);
    }
}
